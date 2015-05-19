package appewtc.masterung.ishiharassru;

/**
 * Created by masterUNG on 5/19/15 AD.
 */
public class SSRUmodel {

    private int buttonAnInt;

    public interface OnSSRUmodelChangeListener {
        void onSSRUmodelChangeListener(SSRUmodel ssrUmodel);
    }   // interface

    private OnSSRUmodelChangeListener onSSRUmodelChangeListener;

    public void setOnSSRUmodelChangeListener(OnSSRUmodelChangeListener onSSRUmodelChangeListener) {
        this.onSSRUmodelChangeListener = onSSRUmodelChangeListener;
    }

    public int getButtonAnInt() {
        return buttonAnInt;
    }   // getter

    public void setButtonAnInt(int buttonAnInt) {
        this.buttonAnInt = buttonAnInt;

        if (this.onSSRUmodelChangeListener != null) {

            this.onSSRUmodelChangeListener.onSSRUmodelChangeListener(this);

        }

    }   // setter

}   // Main Class
