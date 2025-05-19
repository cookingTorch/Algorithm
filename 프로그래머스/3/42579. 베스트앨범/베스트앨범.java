import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

class Solution {
    private static final int NONE = -1;

    private static final class Genre implements Comparable<Genre> {
        private static int idx;

        int sum;
        int first;
        int second;
        int firstPlay;
        int secondPlay;

        Genre() {
            firstPlay = NONE;
            secondPlay = NONE;
        }

        void add(int idx, int play) {
            sum += play;
            if (play > firstPlay) {
                second = first;
                secondPlay = firstPlay;
                first = idx;
                firstPlay = play;
            } else if (play > secondPlay) {
                second = idx;
                secondPlay = play;
            }
        }

        void fill() {
            ans[idx++] = first;
            if (secondPlay != NONE) {
                ans[idx++] = second;
            }
        }

        @Override
        public int compareTo(Genre o) {
            return o.sum - sum;
        }
    }

    private static int[] ans;

    public int[] solution(String[] genres, int[] plays) {
        int i;
        int len;
        int cnt;
        Genre genre;
        HashMap<String, Genre> map;
        ArrayList<Genre> list;

        map = new HashMap<>();
        len = genres.length;
        list = new ArrayList<>();
        cnt = 0;
        for (i = 0; i < len; i++) {
            genre = map.get(genres[i]);
            if (genre == null) {
                map.put(genres[i], genre = new Genre());
                list.add(genre);
                cnt++;
            } else if (genre.secondPlay == NONE) {
                cnt++;
            }
            genre.add(i, plays[i]);
        }
        Collections.sort(list);
        len = list.size();
        ans = new int[cnt];
        for (i = 0; i < len; i++) {
            list.get(i).fill();
        }
        return ans;
    }
}
