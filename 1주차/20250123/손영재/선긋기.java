import java.util.*;

class Point {
    int start;
    int end;

    public Point(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            points.add(new Point(x, y));
        }

        // 시작점을 기준으로 정렬
        points.sort(Comparator.comparingInt(p -> p.start));

        int totalLength = 0;
        int first = points.get(0).start;
        int last = points.get(0).end;

        for (int i = 1; i < points.size(); i++) {
            int start = points.get(i).start;
            int end = points.get(i).end;

            if (start <= last) {
                // 겹치는 경우, 끝점을 확장
                last = Math.max(last, end);
            } else {
                // 겹치지 않는 경우, 이전 선분의 길이를 추가
                totalLength += last - first;
                first = start;
                last = end;
            }
        }
        // 마지막 선분 길이 추가
        totalLength += last - first;

        System.out.println(totalLength);
    }
}
