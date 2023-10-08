package org.jubadeveloper.core.usecases.exceptions;

public class ChannelNotFoundException extends Exception {
    private static final String exceptionMessage = "Channel with id: %d was not found";
    public ChannelNotFoundException (Long id) {
        super(String.format(exceptionMessage, id));
    }
}
