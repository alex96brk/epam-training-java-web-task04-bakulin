package by.epamtc.bakulin.controller.command;

public interface Command {

    void setRequestParameters(String[] requestParameters);

    String execute();

}
