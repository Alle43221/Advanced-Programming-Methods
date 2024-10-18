import java.util.ArrayList;

public class BubbleSorter<E extends Comparable<E>> extends AbstractSorter<E> {
    public BubbleSorter() {
        super();
    }

    @Override
    void sort(ArrayList<E> arrayList) {
        int n=arrayList.size();
        boolean swapped;
        for(int i=0; i<n-1; i++) {
            swapped=false;
            for(int j=0; j<n-i-1; j++) {
                if(arrayList.get(j).compareTo(arrayList.get(j+1))>0) {
                    E temp=arrayList.get(j);
                    arrayList.set(j,arrayList.get(j+1));
                    arrayList.set(j+1,temp);
                    swapped=true;
                }
            }
            if(!swapped) {
                break;
            }
        }
    }
}
