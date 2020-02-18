package zrock.application.engine.widget.callback;

public interface DragStateListener {

    void onDragStart();

    void onDragEnd(boolean isMenuOpened);
}
