package is.command;

import java.awt.geom.Point2D;

public class MoveCommand implements Command {
    @Override
    public boolean doIt() {
        return false;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
/*
    private  final Point2D oldPos;

    private  final Point2D newPos;

    private  final GraphicObject object;

    public MoveCommand(GraphicObject go, Point2D pos) {
        oldPos = go.getPosition();
        newPos = pos;
        this.object = go;


    }

    @Override
    public boolean doIt() {

        object.moveTo(newPos);

        return true;
    }

    @Override
    public boolean undoIt() {
        object.moveTo(oldPos);

        return true;
    }
*/
}