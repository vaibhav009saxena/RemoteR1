package com.skyplus.generic.utils.waitFactory;

/**
 * Exception for WaitFactoryUse.
 *
 * @version 1.0
 */
@SuppressWarnings("serial") // Since we don't need any type of serialization and
// deserialization. Suppressing the warning.
public final class WaitFactoryUseException extends Exception
{

   /**
    * Constructs a checked standard Java exception with the specified cause and a detail message.
    *
    * @param message - the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
    */
   public WaitFactoryUseException(final String message)
   {
      super(message);
   }

   /**
    * Constructs checked standard Java exception with the specified cause and a detail message.
    *
    * @param message - the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
    * @param cause   - the cause (which is saved for later retrieval by the Throwable.getCause() method)
    */
   public WaitFactoryUseException(final String message, final Throwable cause)
   {
      super(message, cause);
   }

   /**
    * Constructs checked standard Java exception with the specified cause and a detail message.
    *
    * @param cause - the cause (which is saved for later retrieval by the Throwable.getCause() method)
    */
   public WaitFactoryUseException(final Throwable cause)
   {
      super(cause);
   }

}
