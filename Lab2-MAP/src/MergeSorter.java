import java.util.ArrayList;

public class MergeSorter<E extends Comparable<E>> extends AbstractSorter<E> {
    public MergeSorter() {
        super();
    }

    private void interclasare(ArrayList<E> lista, int left, int mid, int right) {
        int n1=mid-left+1;
        int n2=right-mid;
        ArrayList<E>L1 = new ArrayList<>(), L2=new ArrayList<>();
        for(int i=0;i<n1;i++) {
            L1.add(lista.get(i+left));
        }
        for(int i=0;i<n2;i++) {
            L2.add(lista.get(i+mid+1));
        }
        int k=left, i=0, j=0;
        while(i<n1 && j<n2) {
            if(L1.get(i).compareTo(L2.get(j)) <= 0) {
                lista.set(k, L1.get(i));
                k++;
                i++;
            }
            else{
                lista.set(k, L2.get(j));
                k++;
                j++;
            }
        }
        while(i<=n1){
            lista.set(k, L1.get(i));
            i++; k++;
        }
        while(j<=n2){
            lista.set(k, L2.get(j));
            j++; k++;
        }
    }

    private void mergeSort(ArrayList<E> lista, int left, int right) {
        if(left>=right) return;
        int mid = (left+right)/2;
        mergeSort(lista, left, mid);
        mergeSort(lista, mid+1, right);
        interclasare(lista, left, mid, right);
    }

    @Override
    void sort(ArrayList<E> arrayList) {
        mergeSort(arrayList, 0, arrayList.size()-1);
    }
}