/**
 * 
 */
package com.battula.srinivas;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * This class holds any type of comparable class.
 * And help you to remove duplicate elements from random type array.
 * @author srinivas
 */
public class DeDup<T extends Comparable<? super T>> {
	/**
	 * Minimum Array length required to process
	 */
	private static final int ARRAY_MIN_LENGTH = 1;
	/** 
	 * Holds input array.
	 */
	private final transient T[] duplicateElements;
	/**
	 * Array which has duplicate elements.
	 * @param inputArray
	 */
	public DeDup(final T... inputArray) {
		duplicateElements = Arrays.copyOf(inputArray,inputArray.length);
	}

	/**
	 * Returns Distinct Elements.
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T[] distinctElements(final Class<T> clazz) {
		int startPosition = 0;
		T[] distinctArray = (T[]) Array.newInstance(clazz, startPosition);
		for (int elementPos = 0; startPosition < duplicateElements.length; startPosition++) {
			boolean intAlreadyPresent = false;
			final T thisInt = duplicateElements[startPosition];
			for (int position = 0; position < startPosition; position++) {
				final T thisPositionInt = duplicateElements[position];
				intAlreadyPresent = thisInt.compareTo(thisPositionInt) == 0;
				if (intAlreadyPresent) {
					break;
				}
			}
			if (!intAlreadyPresent) {
				distinctArray = Arrays.copyOf(distinctArray,
						elementPos + 1);
				distinctArray[elementPos++] = duplicateElements[startPosition];
			}
		}
		return distinctArray;
	}

	/**
	 * Removes duplicates from the supplied randomIntegersArray.
	 * @param clazz
	 * @return ordered array of same type
	 */
	public T[] distinctSortedElements(final Class<T> clazz) {
		final int length = duplicateElements.length;
		Arrays.sort(duplicateElements);
		return distinctElements(clazz, duplicateElements, length);
	}
	/**
	 * Eleminates the duplicate elements and returns unique array.
	 * @param clazz
	 * @param duplicateElements
	 * @param length
	 * @return
	 */
	private T[] distinctElements(final Class<T> clazz, final T[] duplicateElements,
			final int length) {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) Array.newInstance(clazz, 0);
		int distElementPos = 0;
		if (length > ARRAY_MIN_LENGTH) {
			for (int iterationCounter = 0; iterationCounter < length; iterationCounter++) {
				if (Arrays.binarySearch(result,
						duplicateElements[iterationCounter]) < 0) {
					result = Arrays.copyOf(result,
							distElementPos + 1);
					result[distElementPos++] = duplicateElements[iterationCounter];
				}
			}
		}else{
			// For single or zero items in array return same array
			result=duplicateElements;
		}
		return result; 
	}

}
