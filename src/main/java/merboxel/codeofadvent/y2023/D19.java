package merboxel.codeofadvent.y2023;


import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

import static merboxel.codeofadvent.FileReader.readFileAsScanner;

public class D19 {

    public static void main(String[] args) throws IOException {
        P1.run();
        P2.run();
    }

    static Scanner readFile() throws IOException {
        return readFileAsScanner(2023, 19);
    }

    static class P1 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 1 ---------------");
            long result = 0L;
            Scanner sc = readFile();
            Map<String,WorkFlows> workFlows = new HashMap<>();

            while (sc.hasNext()) {
                String line = sc.nextLine();
                if(line.isEmpty())
                    break;
                String[] parts = line.split("[{}]");

                workFlows.put(parts[0],new WorkFlows(parts[1]));
            }

            while(sc.hasNext()) {
                String line = sc.nextLine();
                Part part = new Part(line.subSequence(1,line.length()-1).toString());

                WorkFlows wf = workFlows.get("in");
                assert(null != wf);

                while(true){
                    String next = wf.calcNextWorkFlows(part);
                    wf = workFlows.get(next);
                    if(next.equals("A")) {
                        result += part.total;
                        break;
                    }
                    if(next.equals("R")) {
                        break;
                    }
                }
            }

            System.out.println(result);
            System.out.println("--------------------------------------");
        }
        static class WorkFlows {
            List<WorkFlow> workFlows = new ArrayList<>();
            String def;
            public WorkFlows(String str) {
                String[] parts = str.split(",");
                for(int i = 0; i < parts.length -1; i++)
                    workFlows.add(new WorkFlow(parts[i]));

                def = parts[parts.length-1];
            }

            public String calcNextWorkFlows(Part part) {
                for(WorkFlow wf: workFlows) {
                    if(wf.isWithinRange(part))
                        return wf.next;
                }
                return def;
            }
        }
        static class WorkFlow {
            char id;
            char sign;
            long value;
            String next;

            public WorkFlow(String s) {
                id = s.charAt(0);
                sign = s.charAt(1);
                value = Long.parseLong(s.substring(2).split(":")[0]);
                next = s.substring(2).split(":")[1];
            }

            public boolean isWithinRange(Part part) {
                switch(id) {
                    case 'x' -> {
                        if(sign == '<')
                            return (part.x < value);
                        return (part.x > value);
                    }
                    case 'm' -> {
                        if(sign == '<')
                            return (part.m < value);
                        return (part.m > value);
                    }
                    case 'a' -> {
                        if(sign == '<')
                            return (part.a < value);
                        return (part.a > value);
                    }
                    case 's' -> {
                        if(sign == '<')
                            return (part.s < value);
                        return (part.s > value);
                    }
                }
                return false;
            }
        }

        static class Part {
            long x,m,a,s;
            long total = 0;

            public Part(String str) {
                String[] parts = str.split("[=,]");
                for(int i = 0; i < parts.length; i += 2) {
                    switch(parts[i]) {
                        case "x" -> {
                            x = Long.parseLong(parts[i+1]);
                        }
                        case "m" -> {
                            m = Long.parseLong(parts[i+1]);
                        }
                        case "a" -> {
                            a = Long.parseLong(parts[i+1]);
                        }
                        case "s" -> {
                            s = Long.parseLong(parts[i+1]);
                        }
                    }
                }
                total += x;
                total += m;
                total += a;
                total += s;
            }

            public Part(long x, long m, long a, long s) {
                this.x = x;
                this.m = m;
                this.a = a;
                this.s = s;
            }
        }
    }

    static class P2 {

        public static void main(String[] args) throws IOException {
            run();
        }

        public static void run() throws IOException {
            System.out.println("--------------- Part 2 ---------------");
            long result = 0L;
            Scanner sc = readFile();
            Map<String, WorkFlows> workFlows = new HashMap<>();

            while (sc.hasNext()) {
                String line = sc.nextLine();
                if(line.isEmpty())
                    break;
                String[] parts = line.split("[{}]");

                workFlows.put(parts[0],new WorkFlows(parts[1]));
            }

            Part all = new Part();


            System.out.println(all.totalValue());
            System.out.println("--------------------------------------");
        }

        static class WorkFlows {
            List<WorkFlow> workFlows = new ArrayList<>();
            String def;
            public WorkFlows(String str) {
                String[] parts = str.split(",");
                for(int i = 0; i < parts.length -1; i++)
                    workFlows.add(new WorkFlow(parts[i]));

                def = parts[parts.length-1];
            }
        }
        static class WorkFlow {
            char id;
            char sign;
            long value;
            String next;

            public WorkFlow(String s) {
                id = s.charAt(0);
                sign = s.charAt(1);
                value = Long.parseLong(s.substring(2).split(":")[0]);
                next = s.substring(2).split(":")[1];
            }
        }

        static class Part {
            int[] x,m,a,s;

            public Part() {
                x = new int[] {1,4000};
                m = new int[] {1,4000};
                a = new int[] {1,4000};
                s = new int[] {1,4000};
            }

            public Part(int[] x, int[] m, int[] a, int[] s) {
                this.x = x;
                this.m = m;
                this.a = a;
                this.s = s;
            }

            public boolean isValid() {
                return (x[1] - x[0]) > -1
                        &&
                        (m[1] - m[0]) > -1
                        &&
                        (a[1] - a[0]) > -1
                        &&
                        (s[1] - s[0]) > -1;
            }

            public BigInteger totalValue() {
                if(!isValid())
                    return BigInteger.ZERO;
                long sumX = 0L;
                long sumM = 0L;
                long sumA = 0L;
                long sumS = 0L;
                for(int i = x[0]; i <= x[1]; i ++)
                    sumX += i;
                for(int i = m[0]; i <= m[1]; i ++)
                    sumM += i;
                for(int i = a[0]; i <= a[1]; i ++)
                    sumA += i;
                for(int i = s[0]; i <= s[1]; i ++)
                    sumS += i;
                return BigInteger.valueOf(sumX).multiply(BigInteger.valueOf(sumM)).multiply(BigInteger.valueOf(sumA)).multiply(BigInteger.valueOf(sumS));
            }
        }
    }
}