| S.N. | 		方法及说明   |
| :----: | :------------------: |
|   1  | static <T> Optional<T> absent() 返回没有包含的参考Optional的实例。|
|   2  | abstract Set<T> asSet() 返回true如果对象是一个Optional实例，无论是包含引用彼此相等或两者都不存在。|
|   3  | abstract boolean equals(Object object) 返回没有包含的参考Optional的实例。|
|   4  | static <T> Optional<T> fromNullable(T nullableReference) 如果nullableReference非空，返回一个包含引用Optional实例;否则返回absent()。|
|   5  | abstract T get() 返回所包含的实例，它必须存在。|
|   6  | abstract int hashCode()) 返回此实例的哈希码。|
|   7  | abstract boolean isPresent() 返回true，如果这支架包含一个(非空)的实例。|
|   8  | static <T> Optional<T> of(T reference) 返回包含给定的非空引用Optional实例。|
|   9  | abstract Optional<T> or(Optional<? extends T> secondChoice) 返回此Optional，如果它有一个值存在; 否则返回secondChoice。|			
|   10 | abstract T or(Supplier<? extends T> supplier) 返回所包含的实例(如果存在); 否则supplier.get()。|			
|   11 | abstract T or(T defaultValue) 返回所包含的实例(如果存在);否则为默认值。|			
|   12 | abstract T orNull() 返回所包含的实例(如果存在);否则返回null。|			
|   13 | static <T> Iterable<T> presentInstances(Iterable<? extends Optional<? extends T>> optionals) 从提供的optionals返回每个实例的存在的值，从而跳过absent()。|			
|   14 | abstract String toString()	 返回此实例的字符串表示。|
|   15 | abstract <V> Optional<V> transform(Function<? super T,V> function)如果实例存在，则它被转换给定的功能;否则absent()被返回|