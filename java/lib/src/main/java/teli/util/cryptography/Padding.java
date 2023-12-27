package teli.util.cryptography;

public interface Padding<T> {

    /**
     * Pad data to meet specific size or alignment requirements.
     *
     * @param data the data to pad
     * @param n    the Block Size
     * @return the padded data
     */
    T pad(T data, int n);

}
