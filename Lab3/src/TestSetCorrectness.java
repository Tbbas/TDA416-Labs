/**
 *
 */
public class TestSetCorrectness{
  private boolean testLLSet;
  private boolean testSTS;
  private int numTries;
  private int countRandOps;
  private int countNums;
  public static void main(String[] args) {
    if(args.length >= 0){
      testLLSet = args[0] == 1;
      testSTS = args[0] == 2;
    }
    if(args.length >= 1){
      numTries = args[1];
    }
    if(args.length >= 2){
      countRandOps = args[2];
    }
    if(args.length >= 3){
      countNums = args[3];
    }
    Random rand = new Random();
    if(testLLSet){
      LinkedHashSet set = new LinkedHashSet(new ArrayList<Integer>());
      SortedLinkedListSet<Integer> ownSet = new SortedLinkedListSet<Integer>();
    } else if(testSTS){
      TreeSet set = new TreeSet(new ArrayList<Integer>());
      SplayTreeSet<Integer> ownSet = new SplayTreeSet<Integer>();
    }
    for(int i=0;i<numTries;i++){
      for(int j = 0;j<countRandOps;j++){
        int oper = rand.nextInt(4) + 1;
        if(oper > 1){
          int num = rand.nextInt(countNums);
        }
        if(oper == 1){
          int java = set.size();
          int own = ownSet.size();
        }else if(oper == 2){
          boolean java = set.add(num);
          boolean own = ownSet.add(num);
        }else if(oper == 3){
          boolean java = set.remove(num);
          boolean own = ownSet.remove(num);
        }else{
          boolean java = set.contains(num);
          boolean own = ownSet.contains(num);
        }
        if(java != own){
          System.out.println("java was: " + java + ", own was: " + own + ", oper was: " + oper);
          throw new Exception();
        }
      }
    }
  }
}
