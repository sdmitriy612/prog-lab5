package ru.ifmo.se.io.formatters;

import ru.ifmo.se.io.IOController;
import ru.ifmo.se.io.SafeIOController;
/**
 * Interface representing a serializer that can read and write objects of type T.
 * <p>
 * This interface extends both {@link IOController} and {@link SafeIOController}, providing methods to read, write,
 * and safely handle objects of type T.
 * </p>
 *
 * @param <T> the type of object that this serializer works with
 */
public interface Serializator<T> extends IOController<T>, SafeIOController<T> {
}
