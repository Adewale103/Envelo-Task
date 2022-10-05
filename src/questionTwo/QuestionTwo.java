package questionTwo;

public class QuestionTwo {
    public static void main(String[] args) {
        int[] array = {87,56,44,23,22,11,9,7,4,2};
        int x = 11;
        boolean result = search(array, x);
        System.out.println(result);
    }

    private static boolean search (int [] numbers, int x) {
        int left = 0;
        int right = numbers.length-1;

        while(left <= right){
            int mid = (left+right)/2;

            if(x == numbers[mid]){
                return true;
            }
            else if(x > numbers[mid]){
                right = mid-1;
            }
            else{
                left = mid + 1;
            }
        }
        return false;
    }

}
