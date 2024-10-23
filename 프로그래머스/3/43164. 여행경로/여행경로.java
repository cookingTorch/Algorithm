import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

class Solution {
    private static final int START = getCode("ICN");

    private static final class Airport implements Comparable<Airport> {
        private static int len;
        private static String[] ret;

        int code;
        int degree;
        boolean[] visited;
        String name;
        ArrayList<Airport> to;

        Airport(int code, String name) {
            this.code = code;
            this.name = name;
            to = new ArrayList<>();
        }

        @Override
        public int compareTo(Airport o) {
            return code - o.code;
        }

        private final boolean dfs(int depth) {
            int i;

            ret[depth++] = name;
            if (depth == len) {
                return true;
            }
            for (i = 0; i < degree; i++) {
                if (visited[i]) {
                    continue;
                }
                visited[i] = true;
                if (to.get(i).dfs(depth)) {
                    return true;
                }
                visited[i] = false;
            }
            return false;
        }

        final void init() {
            degree = to.size();
            visited = new boolean[degree];
            Collections.sort(to);
        }

        final String[] toStringArray(int len) {
            Airport.len = len;
            ret = new String[len];
            dfs(0);
            return ret;
        }
    }

    private static final int getCode(String name) {
        return name.charAt(0) << 16 | name.charAt(1) << 8 | name.charAt(2);
    }

    public String[] solution(String[][] tickets) {
        int code;
        Airport u;
        Airport v;
        HashMap<Integer, Airport> airports;

        airports = new HashMap<>();
        for (String[] ticket : tickets) {
            if ((u = airports.get(code = getCode(ticket[0]))) == null) {
                airports.put(code, u = new Airport(code, ticket[0]));
            }
            if ((v = airports.get(code = getCode(ticket[1]))) == null) {
                airports.put(code, v = new Airport(code, ticket[1]));
            }
            u.to.add(v);
        }
        for (Airport airport : airports.values()) {
            airport.init();
        }
        return airports.get(START).toStringArray(tickets.length + 1);
    }
}
