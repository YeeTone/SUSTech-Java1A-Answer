package xyz.listener;

import xyz.model.BoardLocation;
import xyz.view.SquareComponent;

public interface GameListener {
    void onPlayerLeftClick(BoardLocation location, SquareComponent component);
    void onPlayerRightClick(BoardLocation location, SquareComponent component);
    void onPlayerMidClick(BoardLocation location, SquareComponent component);
}
