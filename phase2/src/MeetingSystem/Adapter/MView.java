package MeetingSystem.Adapter;

public interface MView {

    void setPresenter(MPresenter presenter);

    void updateViewFromModel(boolean isFirst);

    void open();
}
