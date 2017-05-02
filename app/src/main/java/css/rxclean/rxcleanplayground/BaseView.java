package css.rxclean.rxcleanplayground;

/**
 * Base Contract to be implemented by all Views
 */


public interface BaseView<T> {

    void setPresenter(T presenter);

}
