package com.skyplus.generic.utils.locatorFactory;

/**
 * Exception for LocatorFactory.
 */
@SuppressWarnings("serial") // Since we don't need any type of serialization and
// deserialization. Suppressing the warning.

public class LocatorFactoryException extends Exception
{

   /**
    * Constructs a checked standard Java exception with the specified cause and a detail message.
    *
    * @param message - the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
    */
   public LocatorFactoryException(final String message)
   {
      super(message);
   }

   /**
    * Constructs checked standard Java exception with the specified cause and a detail message.
    *
    * @param message - the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
    * @param cause   - the cause (which is saved for later retrieval by the Throwable.getCause() method)
    */
   public LocatorFactoryException(final String message, final Throwable cause)
   {
      super(message, cause);
   }

   /**
    * Constructs checked standard Java exception with the specified cause and a detail message.
    *
    * @param cause - the cause (which is saved for later retrieval by the Throwable.getCause() method)
    */
   public LocatorFactoryException(final Throwable cause)
   {
      super(cause);
   }

}
