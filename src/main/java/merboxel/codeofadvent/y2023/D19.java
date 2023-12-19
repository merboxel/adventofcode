package merboxel.codeofadvent.y2023;


import java.io.IOException;
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
            Map<String, P1.WorkFlows> workFlows = new HashMap<>();

            while (sc.hasNext()) {
                String line = sc.nextLine();
                if(line.isEmpty())
                    break;
                String[] parts = line.split("[{}]");

                workFlows.put(parts[0],new P1.WorkFlows(parts[1]));
            }

            while(sc.hasNext()) {
                String line = sc.nextLine();
                P1.Part part = new P1.Part(line.subSequence(1,line.length()-1).toString());

                P1.WorkFlows wf = workFlows.get("in");
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
            List<P1.WorkFlow> workFlows = new ArrayList<>();
            String def;
            public WorkFlows(String str) {
                String[] parts = str.split(",");
                for(int i = 0; i < parts.length -1; i++)
                    workFlows.add(new P1.WorkFlow(parts[i]));

                def = parts[parts.length-1];
            }

            public String calcNextWorkFlows(P1.Part part) {
                for(P1.WorkFlow wf: workFlows) {
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

            public boolean isWithinRange(P1.Part part) {
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
}