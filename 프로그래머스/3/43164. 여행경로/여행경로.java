import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Solution {
    private static final Airport START = new Airport("ICN");

    private static final class Airport implements Comparable<Airport> {
        private static int len;
        private static String[] ret;

        int code;
        int degree;
        boolean[] visited;
        String name;
        ArrayList<Airport> to;

        Airport(String name) {
            this.name = name;
            this.code = getCode(name);
            to = new ArrayList<>();
        }

        @Override
        public int compareTo(Airport o) {
            return code - o.code;
        }

        private static final int getCode(String name) {
            return name.charAt(0) << 16 | name.charAt(1) << 8 | name.charAt(2);
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

    public String[] solution(String[][] tickets) {
        Airport u;
        Airport v;
        HashMap<String, Airport> airports;

        airports = new HashMap<>();
        airports.put(START.name, START);
        for (String[] ticket : tickets) {
            if ((u = airports.get(ticket[0])) == null) {
                airports.put(ticket[0], u = new Airport(ticket[0]));
            }
            if ((v = airports.get(ticket[1])) == null) {
                airports.put(ticket[1], v = new Airport(ticket[1]));
            }
            u.to.add(v);
        }
        for (Airport airport : airports.values()) {
            airport.init();
        }
        return START.toStringArray(tickets.length + 1);
    }
}
