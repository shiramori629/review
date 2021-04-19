import java.util.Arrays;

public class InsertSort {
  public static void insertSort(int[] a) {
    int i, j, insertNote;// data for insert
    // a[0..i-1] sorted a[i..]i to the end
    for (i = 1; i < a.length; i++) {// recursive from second element, insert the element
      insertNote = a[i];// set the second element as first insert data
      j = i - 1;
      while (j >= 0 && insertNote < a[j]) {
        a[j + 1] = a[j];// if insert smaller than j, move j to back
        j--;
      }
      a[j + 1] = insertNote;// until insert not smaller than j, put insertnote to the sort
    }
  }

  public static void main(String[] args) {
    int a[] = {38, 65, 97, 76, 13, 27, 49};
    insertSort(a);
    System.out.println(Arrays.toString(a));
  }
}
