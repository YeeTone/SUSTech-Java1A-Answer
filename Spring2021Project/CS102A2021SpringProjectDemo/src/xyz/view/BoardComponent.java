package xyz.view;

import xyz.listener.GameListener;
import xyz.model.BoardLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.util.*;
import java.util.List;

public class BoardComponent extends JComponent {
    private List<GameListener> listenerList = new ArrayList<>();
    private SquareComponent[][] gridComponents;
    private int row;
    private int col;
    private int gridSize;

    public BoardComponent (int row, int col, int rowLength, int colLength) {
        enableEvents(MouseEvent.MOUSE_EVENT_MASK);
        setLayout(null);
        setSize(rowLength, colLength);

        this.row = row;
        this.col = col;
        this.gridSize = colLength / col;
        gridComponents = new SquareComponent[row][col];
        initialGridComponent();
    }

    private void initialGridComponent () {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                gridComponents[i][j] = new SquareComponent(gridSize);
                gridComponents[i][j].setLocation(i * gridSize, j * gridSize);
                add(gridComponents[i][j]);
            }
        }
    }

    public SquareComponent getGridAt(BoardLocation location) {
        return gridComponents[location.getRow()][location.getColumn()];
    }

    private BoardLocation getLocationByPosition(int x, int y) {
        return new BoardLocation(x / gridSize, y / gridSize);
    }

    private void removeItemAt (BoardLocation location) {
        getGridAt(location).removeAll();
        getGridAt(location).revalidate();
    }

    public void setItemAt (BoardLocation location, int num) {
        removeItemAt(location);
        getGridAt(location).add(new ItemComponent(num));
    }

    private void flagGrid (BoardLocation location) {

    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);
        if (e.getID() != MouseEvent.MOUSE_PRESSED) return;
        if (e.getButton() == MouseEvent.BUTTON1) {
            // TODO: 2021/4/13 left mouse button
            JComponent clickedComponent = (JComponent) getComponentAt(e.getX(), e.getY());
            BoardLocation location = getLocationByPosition(e.getX(), e.getY());
            for (GameListener listener : listenerList) {
                listener.onPlayerLeftClick(location, (SquareComponent) clickedComponent);
            }
        } else if (e.getButton() == MouseEvent.BUTTON2) {
            // TODO: 2021/4/13 middle mouse button
            JComponent clickedComponent = (JComponent) getComponentAt(e.getX(), e.getY());
            BoardLocation location = getLocationByPosition(e.getX(), e.getY());
            for (GameListener listener : listenerList) {
                listener.onPlayerMidClick(location, (SquareComponent) clickedComponent);
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            // TODO: 2021/4/13 right mouse button
            JComponent clickedComponent = (JComponent) getComponentAt(e.getX(), e.getY());
            BoardLocation location = getLocationByPosition(e.getX(), e.getY());
            for (GameListener listener : listenerList) {
                listener.onPlayerRightClick(location, (SquareComponent) clickedComponent);
            }
        }
    }

    public void registerListener(GameListener listener) {
        listenerList.add(listener);
    }

    public void unregisterListener(GameListener listener) {
        listenerList.remove(listener);
    }
}
