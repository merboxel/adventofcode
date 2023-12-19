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
            BigInteger result = new BigInteger("0");
            Scanner sc = readFile();
            Map<String, WorkFlows> workFlows = new HashMap<>();

            while (sc.hasNext()) {
                String line = sc.nextLine();
                if(line.isEmpty())
                    break;
                String[] parts = line.split("[{}]");

                workFlows.put(parts[0],new WorkFlows(parts[1]));
            }

            Stack<SetPart> stack = new Stack<>();

            stack.add(new SetPart(new Part(), "in"));

            while(!stack.isEmpty()) {
                SetPart elem = stack.pop();
                if(elem.nameWorkflow.equals("R")) {
                    continue;
                }
                if(elem.nameWorkflow.equals("A")) {
                    BigInteger x = elem.part.totalValue();
                    result = result.add(x);
                    continue;
                }
//                System.out.println("nameWorkFlow: '"+elem.nameWorkflow+"'");
                WorkFlows workFlow = workFlows.get(elem.nameWorkflow);
                stack.addAll(workFlow.calcRange(elem.part));
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

            public List<SetPart> calcRange(Part part) {
                List<SetPart> list = new ArrayList<>();
                for(WorkFlow workFlow: workFlows) {
                    if(!part.isValid()) {
                        break;
                    }
                    list.addAll(workFlow.calcRange(part));
                }
                if(part.isValid()) {
                    list.add(new SetPart(part,def));
                }
                return list;
            }
        }
        static class WorkFlow {
            char id;
            char sign;
            int value;
            String next;

            public WorkFlow(String s) {
                id = s.charAt(0);
                sign = s.charAt(1);
                value = Integer.parseInt(s.substring(2).split(":")[0]);
                next = s.substring(2).split(":")[1];
            }

            public List<SetPart> calcRange(Part part) {
                int[] range = part.map.get(id);

                List<SetPart> list = new ArrayList<>();

//                System.out.println("-----------------------");
//                System.out.println("sign: '"+sign+"', value: '"+value+"'");
//                System.out.println("start: ["+range[0]+","+range[1]+"]");
                if(sign == '<') {
//                    if(range[1] < value) {
//                        range[1] = -1;
//                        list.add(new SetPart(new Part(part.map),next));
//                    } else if(range[0] >= value) {
//                        range[1] = -1;
//                    } else {
                        Part p = new Part(part.map);
                        p.setMapElem(id, new int[]{range[0], value - 1});
                        int tmp = range[0];
                        range[0] = value;

//                        System.out.println("split: ["+tmp+","+(value - 1)+"]");
                        list.add(new SetPart(p,next));
//                    }
                }
                if(sign == '>'){
//                    if(range[1] < value) {
//                        range[1] = -1;
//                    }else if(range[0] >= value) {
//                        range[1] = -1;
//                        list.add(new SetPart(new Part(part.map),next));
//                    } else {
                        Part p = new Part(part.map);
                        p.setMapElem(id, new int[]{value + 1, range[1]});
                        int tmp = range[1];
                        range[1] = value;
//                        System.out.println("split: ["+(value + 1)+","+tmp+"]");

                        list.add(new SetPart(p, next));
                    }
//                }
//                System.out.println("end: ["+range[0]+","+range[1]+"]");
//                System.out.println("-----------------------");
                return list;
            }
        }

        static class SetPart {
            Part part;
            String nameWorkflow;

            public SetPart(Part part, String nameWorkflow) {
                this.part = part;
                this.nameWorkflow = nameWorkflow;
            }
        }

        static class Part {
            Map<Character,int[]> map = new HashMap<>();

            public Part() {
                map.put('x',new int[] {1,4000});
                map.put('m',new int[] {1,4000});
                map.put('a',new int[] {1,4000});
                map.put('s',new int[] {1,4000});
            }

            public Part(int[] x, int[] m, int[] a, int[] s) {
                map.put('x',x);
                map.put('m',m);
                map.put('a',a);
                map.put('s',s);
            }

            public Part(Map<Character,int[]> map) {
                for(Character c: map.keySet()) {
                    this.map.put(c,map.get(c).clone());
                }
            }

            public void setMapElem(char c, int[] range) {
                this.map.put(c,range);
            }

            public boolean isValid() {
                return (map.get('x')[1] - map.get('x')[0]) > -1
                        &&
                        (map.get('m')[1] - map.get('m')[0]) > -1
                        &&
                        (map.get('a')[1] - map.get('a')[0]) > -1
                        &&
                        (map.get('s')[1] - map.get('s')[0]) > -1;
            }

            public BigInteger totalValue() {
                if(!isValid())
                    return BigInteger.ZERO;
                long sumX = map.get('x')[1] - map.get('x')[0]+1;
                long sumM = map.get('m')[1] - map.get('m')[0]+1;
                long sumA = map.get('a')[1] - map.get('a')[0]+1;
                long sumS = map.get('s')[1] - map.get('s')[0]+1;
                return BigInteger.valueOf(sumX).multiply(BigInteger.valueOf(sumM)).multiply(BigInteger.valueOf(sumA)).multiply(BigInteger.valueOf(sumS));
            }
        }
    }
}