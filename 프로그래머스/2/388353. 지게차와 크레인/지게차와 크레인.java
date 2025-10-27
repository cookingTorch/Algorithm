import java.util.ArrayDeque;

/**
 * 물류창고 테두리에 빈 공간 한 줄씩 추가
 * 빈 공간 테두리에 벽 추가
 * (1, 1)에서 BFS 실행 시 외부와 연결된 바깥쪽 칸들을 지정 가능
 * 알파벳 별 좌표 목록에 컨테이너들 삽입
 * 바깥쪽 컨테이너들은 알파벳 별 바깥쪽 좌표 목록에도 삽입
 * 크레인: 알파벳에 해당하는 전체 좌표 목록에서 출고
 * 지게차: 알파벳에 해당하는 바깥쪽 좌표 목록에서 출고
 * 출고 시 바깥쪽 컨테이너이면 BFS 큐에 삽입하여
 * 다음 바깥쪽 칸들 계산 시 사용
 */
class Solution {
    private static final int ROW = 6;
    private static final int COL = (1 << ROW) - 1;
    private static final int DIFF = 'A' - 1;
    private static final int ALPHA = 26;
    private static final int EMPTY = 0;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int cnt;
    private static int[][] map;
    private static boolean[][] isOuter;
    private static ArrayDeque<Integer> q;
    private static ArrayDeque<Integer>[] total;
    private static ArrayDeque<Integer>[] outer;

    private static void bfs() { // 바깥쪽 컨테이너를 표시하는 BFS
        int x;
        int y;
        int i;
        int nx;
        int ny;
        int cur;

        while (!q.isEmpty()) {
            cur = q.pollFirst(); // 현재 칸
            x = cur >>> ROW; // x 좌표
            y = cur & COL; // y 좌표
            for (i = 0; i < 4; i++) { // 4방 탐색
                nx = x + dx[i]; // 다음 x 좌표
                ny = y + dy[i]; // 다음 y 좌표
                if (!isOuter[nx][ny]) { // 바깥쪽 컨테이너 표시가 아직 안되어 있으면
                    if (map[nx][ny] == EMPTY) { // 컨테이너 없는 경우
                        q.addLast(nx << ROW | ny); // 큐에 삽입
                    } else { // 컨테이너 존재
                        outer[map[nx][ny]].addLast(nx << ROW | ny); // 바깥쪽 컨테이너 목록에 추가
                    }
                    isOuter[nx][ny] = true; // 바깥쪽 컨테이너로 표시
                }
            }
        }
    }

    private static void lift(int val) { // 지게차
        int x;
        int y;
        int cur;
        ArrayDeque<Integer> pos;

        pos = outer[val]; // 해당 알파벳의 바깥쪽 컨테이너 좌표
        while (!pos.isEmpty()) { // 목록 순회
            cur = pos.pollFirst(); // 컨테이너
            x = cur >>> ROW; // x 좌표
            y = cur & COL; // y 좌표
            if (map[x][y] != EMPTY) { // 컨테이너 존재
                map[x][y] = EMPTY; // 꺼냄
                cnt--; // 컨테이너 개수 감소
                q.addLast(cur); // BFS 시 사용
            }
        }
    }

    private static void crane(int val) { // 크레인
        int x;
        int y;
        int cur;
        ArrayDeque<Integer> pos;

        pos = total[val]; // 해당 알파벳의 모든 컨테이너 좌표
        while (!pos.isEmpty()) { // 목록 순회
            cur = pos.pollFirst(); // 컨테이너
            x = cur >>> ROW; // x 좌표
            y = cur & COL; // y 좌표
            if (map[x][y] != EMPTY) { // 컨테이너 존재
                map[x][y] = EMPTY; // 꺼냄
                cnt--; // 컨테이너 개수 감소
                if (isOuter[x][y]) { // 바깥쪽 컨테이너이면
                    q.addLast(cur); // BFS 시 사용
                }
            }
        }
    }

    public int solution(String[] storage, String[] requests) {
        int n;
        int m;
        int i;
        int j;
        int val;

        total = new ArrayDeque[ALPHA + 1]; // 알파벳 별 전체 컨테이너 목록
        outer = new ArrayDeque[ALPHA + 1]; // 알파벳 별 바깥쪽 컨테이너 목록
        for (i = 1; i <= ALPHA; i++) { // 알파벳 순회
            total[i] = new ArrayDeque<>(); // 좌표 목록 생성
            outer[i] = new ArrayDeque<>(); // 좌표 목록 생성
        }
        n = storage.length + 4; // 세로 길이 + 빈 공간 + 벽
        m = storage[0].length() + 4; // 가로 길이 + 빈 공간 + 벽
        map = new int[n][m]; // 물류창고
        isOuter = new boolean[n][m]; // 외부와 연결된 바깥쪽 칸인지 표시
        for (i = 2; i < n - 2; i++) { // 컨테이너 순회
            for (j = 2; j < m - 2; j++) {
                val = storage[i - 2].charAt(j - 2) - DIFF; // 알파벳 -> 숫자
                map[i][j] = val; // 지도에 기록
                total[val].addLast(i << ROW | j); // 전체 컨테이너 목록에 추가
            }
        }
        for (i = 0; i < m; i++) {
            isOuter[0][i] = true; // 위쪽 벽 두르기
        }
        for (i = 0; i < n; i++) {
            isOuter[i][0] = true; // 왼쪽 벽 두르기
            isOuter[i][m - 1] = true; // 오른쪽 벽 두르기
        }
        System.arraycopy(isOuter[0], 0, isOuter[n - 1], 0, m); // 아래쪽 벽 두르기
        cnt = (n - 4) * (m - 4); // 초기 컨테이너 개수
        q = new ArrayDeque<>(); // BFS에 사용할 큐
        q.addLast(1 << ROW | 1); // (1, 1) 삽입
        isOuter[1][1] = true; // (1, 1)은 바깥쪽 칸임을 표시
        for (String request : requests) { // 출고 요청 순회
            val = request.charAt(0) - DIFF; // 꺼낼 컨테이너 알파벳 -> 숫자
            if (request.length() == 1) { // 지게차일 경우
                bfs(); // BFS: 외부와 연결된 바깥쪽 칸들을 계산
                lift(val); // 지게차 출고
            } else { // 크레인일 경우
                bfs(); // BFS: 외부와 연결된 바깥쪽 칸들을 계산
                crane(val); // 크레인 출고
            }
        }
        return cnt; // 남은 컨테이너 개수 출력
    }
}
