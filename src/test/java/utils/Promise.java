package utils;

@FunctionalInterface
public interface Promise<T> {

    T await();
}
