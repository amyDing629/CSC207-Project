package MeetingSystem.GUIView;

import MeetingSystem.Adapter.MPresenter;

public interface MView {

    void setPresenter(MPresenter presenter);

    void updateViewFromModel(boolean isFirst);

    void open();
}
