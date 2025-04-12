package ru.ifmo.se.io;

import ru.ifmo.se.io.files.WrongChecksumException;
/**
 * The `SafeIOController` interface is designed for handling input and output operations
 * with data that require checksum validation to ensure data integrity.
 *
 * @param <T> the type of data being handled
 */
public interface SafeIOController<T> {
    /**
     * Safely reads data and ensures its integrity by verifying its checksum.
     *
     * @return the data read
     * @throws WrongChecksumException if the checksum of the data is incorrect
     */
    T safeRead() throws WrongChecksumException;
    /**
     * Safely writes the given data and ensures its integrity by calculating and saving its checksum.
     *
     * @param data the data to be written
     */
    void safeWrite(T data);
}
