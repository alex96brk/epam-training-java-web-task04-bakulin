package by.epamtc.bakulin.controller.command;

public interface Command {

    String execute();

    void setRequestParameters(String[] requestParameters);

}
