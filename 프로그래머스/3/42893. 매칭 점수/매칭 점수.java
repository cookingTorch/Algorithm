import java.util.ArrayList;
import java.util.HashMap;

/**
 * 각 페이지의 검색어 개수, URL, 링크 저장
 * 페이지별 매칭점수 계산
 * 매칭점수가 가장 높은 페이지 반환
 */
class Solution {
    private static final char A = 'A';
    private static final char Z = 'Z';
    private static final char a = 'a';
    private static final char z = 'z';
    private static final char NULL = '\0';
    private static final char BRACE = '>';
    private static final char QUOTE = '"';
    private static final char[] META = "<meta ".toCharArray();
    private static final char[] HREF = "<a href=\"".toCharArray();
    private static final char[] CONTENT = "content=\"".toCharArray();
    private static final int NONE = -1;
    private static final int DIFF = a - A;
    private static final int MAX_LEN = 1_501;
    private static final int META_LEN = META.length;
    private static final int HREF_LEN = HREF.length;
    private static final int CONTENT_LEN = CONTENT.length;

    private static int strLen;
    private static int pageLen;
    private static int strStart;
    private static char[] str;
    private static char[] page;

    private static char lower(char ch) { // 소문자 변환
        if (ch < a) { // 대문자일 경우
            return (char) (ch + DIFF); // 소문자로 변환하여 반환
        }
        return ch; // 소문자이면 그대로 반환
    }

    private static boolean isLetter(char ch) { // 알파벳인지
        return (A <= ch && ch <= Z) || (a <= ch && ch <= z); // A ~ Z 혹은 a ~ z
    }

    private static boolean isStr(int i) { // 검색어인지 확인
        int j;

        if (strStart == NONE) { // 저장된 단어 시작 위치 없음
            if (isLetter(page[i])) { // 현재 글자가 알파벳
                strStart = i; // 현재 위치에서 단어가 시작
            }
            return false; // 검색어 불일치
        } else if (!isLetter(page[i])) { // 현재 글자가 알파벳이 아님
            if (i - strStart != strLen) { // 저장된 단어 시작부터 현재 위치 전까지가 검색어 길이와 다름
                strStart = NONE; // 단어 시작 위치 삭제
                return false; // 검색어 불일치
            }
            i = strStart; // 저장된 단어 시작부터 비교
            strStart = NONE; // 단어 시작 위치 삭제
            for (j = 0; j < strLen; j++) { // 검색어와 글자 비교
                if (lower(page[i + j]) != str[j]) { // 검색어와 다름
                    return false; // 검색어 불일치
                }
            }
            return true; // 검색어 일치
        }
        return false; // 검색어 불일치
    }

    private static boolean isPattern(int i, char[] pat, int patLen) { // 패턴과 일치하는지 확인
        int j;

        if (i + patLen > pageLen) { // 페이지를 벗어남
            return false;
        }
        for (j = 0; j < patLen; j++) { // 패턴과 글자 비교
            if (page[i + j] != pat[j]) { // 패턴 불일치
                return false;
            }
        }
        return true; // 패턴 일치
    }

    private static String getUrl(int start) { // URL 추출
        int i;

        for (i = start; i < pageLen; i++) { // 다음 쌍따옴표 전까지 기록
            if (page[i] == QUOTE) { // 쌍따옴표 등장
                break;
            }
        }
        return new String(page, start, i - start);
    }

    public int solution(String word, String[] pages) {
        int i;
        int j;
        int idx;
        int ans;
        int pagesLen;
        int[] strCnt;
        double max;
        double linkScore;
        double[] scores;
        boolean hasUrl;
        ArrayList<String>[] links;
        HashMap<String, Integer> map;

        str = word.toCharArray(); // 검색어
        strLen = str.length; // 검색어 길이
        for (i = 0; i < strLen; i++) { // 검색어 소문자 변환
            str[i] = lower(str[i]);
        }
        pagesLen = pages.length; // 페이지 개수
        strCnt = new int[pagesLen]; // 각 페이지의 검색어 개수
        links = new ArrayList[pagesLen]; // 각 페이지의 링크들
        map = new HashMap<>(); // 링크별 페이지 번호
        page = new char[MAX_LEN]; // 페이지 내용
        for (idx = 0; idx < pagesLen; idx++) { // 각 페이지 순회
            pageLen = pages[idx].length(); // 페이지 내용 길이
            pages[idx].getChars(0, pageLen, page, 0); // 페이지 내용 복사
            page[pageLen++] = NULL; // 마지막에 NULL 문자 삽입
            links[idx] = new ArrayList<>(); // 페이지의 링크들
            hasUrl = false; // url 탐색 여부 초기화
            strStart = NONE; // 단어 시작 위치 초기화
            for (i = 0; i < pageLen; i++) { // 페이지 내용 읽기
                if (isStr(i)) { // 검색어이면
                    strCnt[idx]++; // 검색어 개수 추가
                }
                if (!hasUrl && isPattern(i, META, META_LEN)) { // meta 태그이면
                    for (j = i + META_LEN; j < pageLen; j++) { // meta 태그 이후부터 순회
                        if (page[j] == BRACE) { // 태그 닫힘
                            break;
                        }
                        if (isPattern(j, CONTENT, CONTENT_LEN)) { // content가 존재하면
                            map.put(getUrl(j + CONTENT_LEN), idx); // 현재 페이지의 URL 저장
                            hasUrl = true; // 해당 페이지 url 탐색 완료
                            break;
                        }
                    }
                }
                if (isPattern(i, HREF, HREF_LEN)) { // a 태그의 href 이면
                    links[idx].add(getUrl(i + HREF_LEN)); // 링크 추가
                }
            }
        }
        scores = new double[pagesLen]; // 매칭점수
        for (idx = 0; idx < pagesLen; idx++) { // 각 페이지
            scores[idx] += strCnt[idx]; // 기본점수
            linkScore = (double) strCnt[idx] / links[idx].size(); // (기본점수 / 링크 수)
            for (String link : links[idx]) { // 링크 순회
                if (map.containsKey(link)) { // 링크에 해당하는 웹 페이지 존재
                    scores[map.get(link)] += linkScore; // 해당하는 웹 페이지의 링크점수 추가
                }
            }
        }
        ans = 0; // 매칭 점수가 가장 높은 페이지
        max = 0.0; // 최대 매칭 점수
        for (idx = 0; idx < pagesLen; idx++) { // 매칭 점수가 가장 높은 페이지 번호 계산
            if (scores[idx] > max) { // 최대 매칭 점수보다 높은 페이지
                ans = idx; // 매칭 점수가 가장 높은 페이지 업데이트
                max = scores[idx]; // 최대 매칭 점수 업데이트
            }
        }
        return ans; // 매칭 점수가 가장 높은 페이지 반환
    }
}
