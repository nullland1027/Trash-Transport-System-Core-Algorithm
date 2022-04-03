import Objects.Location;

import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.io.*;
import java.lang.System;

public class Hamilton {
    //private Random rnd = new Random();
    private int start;
    public List<Location> unSort; //待排序的列表
    public static int count = 0;
    private int[][] edge;
    public int[] s;  //最终输出路径
    public int[] t;  //临时路径

    private Stack<Node>[] stack;

    private boolean[] found;

    private int weight = 0;

    private int z = 1;

    Hamilton(List<Location> in) {
        unSort = in;
    }

    public void initialFoudEdge(int count) {
        found = new boolean[count];
        for (int i = 0; i < count; i++) {
            found[i] = false;
        }
    }

    /**
     * 生成边
     * @param count
     */
    public void generateEdge(int count) {
        stack = new Stack[count];
        for (int i = 0; i < count; i++) {
            stack[i] = new Stack<Node>();
        }

        s = new int[count + 1];
        t = new int[count + 1];
        for (int i = 0; i < count + 1; i++) {
            s[i] = -1;
        }
        for (int i = 0; i < count + 1; i++) {
            t[i] = -1;
        }

        edge = new int[count][count];
        for (int i = 0; i < count; i++) {
            for (int j = i; j < count; j++) {
                if (i != j) {
                    //edge[i][j] = rnd.nextInt(count * count) + 1; //i到j的距离
                    edge[i][j] = (int) Location.calDistance(
                            Main.communityLocationList.get(i),
                            Main.communityLocationList.get(j));
                    edge[j][i] = edge[i][j];
                } else {
                    edge[i][j] = 0;  //到自身的距离
                }
            }
        }
    }


    /**
     * 输出边
     * @param
     */
//    public void outEdge(int count) {
//        for (int i = 0; i < count; i++) {
//            for (int j = 0; j < count; j++) {
//                System.out.print(Integer.toString(edge[i][j]) + ' ');
//            }
//            System.out.println();
//        }
//    }


    public void outPath() {
        //System.out.print("第" + z + "条路径为：0 ");
        System.out.println("最短路径为");
        for (int i = 0; i < s.length - 1; i++) {
            System.out.print(Integer.toString(s[i]) + ' ');
        }
        System.out.println(s[s.length - 1]);
    }

    public int[] getS() {
        return s;
    }


    public void transform() {
        if (count >= 0) System.arraycopy(t, 0, s, 0, count);
        s[count] = weight;
    }

    /**
     * 贪心算法查找
     * @param temp
     */
    public void greedyFind(int temp) {
        start = temp;
        found[temp] = true;
        Node c;
        int dindex = -1;
        int start = temp;
        int k = count - 1;

        for (int i = start; k > 0; ) {
            int min = 60000;
            for (int j = 0; j < count; j++) {
                if (j != 0 && edge[i][j] > 0 && !found[j]
                        && min > edge[i][j]) {
                    min = edge[i][j];
                    dindex = j;
                }
            }
            weight += min;
            found[dindex] = true;
            t[count - 1 - k] = dindex;
            i = dindex;
            k--;
            if (k == 0) {
                t[count - 1] = 0;
                weight += edge[i][temp];
            }
        }
        transform();
        s[s.length - 1] = weight;
    }

    /**
     * 回溯法查找
     *
     * @param start
     */
    public void find(int start) {
        this.start = start;
        int iStart = start;
        Node c;
        int nweight = 0;
        found[iStart] = true;
        int k = 0;

        for (int i = start; ; ) {
            found[i] = true;
            if (stack[k].empty()) {
                for (int j = 0; j < count; j++) {
                    if (!found[j] && j != i && edge[i][j] > 0
                            && nweight + edge[i][j] < weight) {
                        Node temp = new Node(j, edge[i][j], i);
                        stack[k].push(temp);
                    }
                }
            }
            if (!stack[k].empty()) {
                int l = stack[k].peek().nodeNumber;
                t[k] = l;
                nweight = nweight + edge[i][l];

                i = l;
                k++;
            } else {
                if (k == count - 1) {
                    if (nweight + edge[i][start] < weight) {
                        t[k] = start;
                        weight = nweight + edge[i][start];//每次输出时权值在变，所以每次比较都与当前的最短路径权值比较。
                        transform();
                        //outPath();
                        s[s.length - 1] = nweight + edge[i][start];

                    }
                    while (stack[k].empty()) {
                        c = stack[--k].pop();
                        i = c.lastNodeNumber;
                        found[c.nodeNumber] = false;
                        nweight = nweight - c.addWeight;
                        if (stack[0].empty()) {
                            break;
                        }
                    }


                } else {
                    while (stack[k].empty()) {
                        c = stack[--k].pop();
                        i = c.lastNodeNumber;
                        found[c.nodeNumber] = false;
                        nweight = nweight - c.addWeight;
                        if (stack[0].empty()) {
                            break;
                        }
                    }

                }
            }

            if (stack[0].empty()) {
                break;
            }
        }

    }

    /**
     * 定义的节点类
     */
    class Node {
        int nodeNumber;
        int lastNodeNumber;
        int addWeight;

        Node(int nodeNumber, int addWeight, int lastNodeNumber) {
            this.nodeNumber = nodeNumber;
            this.addWeight = addWeight;
            this.lastNodeNumber = lastNodeNumber;
        }
    }

    public int[] getAnswer(List<Location> inFunction) {
        Hamilton temp = new Hamilton(inFunction);
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader brs = new BufferedReader(is);
        //System.out.print("输入顶点数：");
        String number = null;
        try {
//            number = brs.readLine();
//            count = Integer.parseInt(number);
            count = Main.numOfLocation;
        } catch (Exception e) {
            e.printStackTrace();
        }

        temp.generateEdge(count);
        temp.initialFoudEdge(count);


        // TODO
        // temp.outEdge(count);



        temp.greedyFind(0);
        // System.out.println("每条路径均从第0号节点出发，最后回到第0号节点。");

        System.out.println("第一条路径为先用贪心算法找到的局部最优解。");
        temp.outPath();
        temp.initialFoudEdge(count);

        System.out.println("以下是通过回溯限界法与局部最优相比较找到的其余更短路径。");

        temp.find(0);
        temp.outPath();
        return temp.s;
    }

}
