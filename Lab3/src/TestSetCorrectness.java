import java.util.*;

/**
 *
 */
public class TestSetCorrectness{
  private static boolean testLLSet;
  private static boolean testSTS;
  private static int numTries;
  private static int countRandOps;
  private static int countNums;
    final private static String[] translate = {"size", "add", "remove", "contans"};
    private static Set set;

    private static SimpleSet ownSet;
  public static void main(String[] args) throws Exception {
    if(args.length >= 0){
      testLLSet = Integer.parseInt(args[0]) == 1;
      testSTS = Integer.parseInt(args[0]) == 2;
    }
    if(args.length >= 1){
      numTries = Integer.parseInt(args[1]);
    }
    if(args.length >= 2){
      countRandOps = Integer.parseInt(args[2]);
    }
    if(args.length >= 3){
      countNums = Integer.parseInt(args[3]);
    }
    Random rand = new Random();
    if(testLLSet){
      set = new LinkedHashSet(new ArrayList<Integer>());
      ownSet = new SortedLinkedListSet<Integer>();
    } else if(testSTS){
      set = new TreeSet(new ArrayList<Integer>());
      ownSet = new SplayTreeSet<Integer>();
    }
    for(int i=0;i<numTries;i++){
      for(int j = 0;j<countRandOps;j++){
        int num = 0;
          int java;
          int own;
        int oper = rand.nextInt(4) + 1;
        if(oper > 1){
          num = rand.nextInt(countNums);
        }
        if(oper == 1){
          java = set.size();
          own = ownSet.size();
        }else if(oper == 2){
          java = set.add(num) ? 1 : 0;
          own = ownSet.add(num) ? 1 : 0;
        }else if(oper == 3){
          java = set.remove(num) ? 1 : 0;
          own = ownSet.remove(num) ? 1 : 0;
        }else{
          java = set.contains(num) ? 1 : 0;
          own = ownSet.contains(num) ? 1 : 0;
        }
        if(java != own){
          String ret = "ERR java was: " + java + ", own was: " + own + ", oper was: " + translate[oper-1];
            if(oper>1){
                ret += ", num: " + num;
            }
            System.out.println(ret);
          throw new Exception();
        }else{
            String ret = "OK java was: " + java + ", own was: " + own + ", oper was: " + translate[oper-1];
            if(oper>1){
                ret += ", num: " + num;
            }
        }
      }
    }
  }
}
