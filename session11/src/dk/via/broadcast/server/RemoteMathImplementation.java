package dk.via.broadcast.server;

import dk.via.broadcast.model.Expression;
import dk.via.broadcast.model.Result;
import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteMathImplementation extends UnicastRemoteObject implements RemoteMath {
    public RemoteMathImplementation() throws RemoteException {
    }

    @Override
    public Result evaluate(Expression e) throws RemoteException {
        double operand1 = e.getOperand1();
        double operand2 = e.getOperand2();
        double r = switch(e.getOperator()) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            case "/" -> operand1 / operand2;
            default -> throw new RemoteException("Illegal operand");
        };
        Result result = new Result(r, e);
        return result;
    }

    @Override
    public void addPropertyChangeListener(RemotePropertyChangeListener<Result> listener) {
    }

    @Override
    public void removePropertyChangeListener(RemotePropertyChangeListener<Result> listener) {
    }
}
