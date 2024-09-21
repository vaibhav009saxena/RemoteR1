package com.skyplus.generic.utils.commonFunctions;

/**
 * Exception for CommonFunction.
 */
@SuppressWarnings("serial") // Since we don't need any type of serialization
// and deserialization. Suppressing the warning.
public final class CommonFunctionException extends Exception
{

   /**
    * Constructs a checked standard Java exception with the specified cause and a detail message.
    *
    * @param message - the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
    */
   public CommonFunctionException(final String message)
   {
      super(message);
   }

   /**
    * Constructs checked standard Java exception with the specified cause and detail message.
    *
    * @param message - the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
    * @param cause   - the cause (which is saved for later retrieval by the Throwable.getCause() method)
    */
   public CommonFunctionException(final String message, final Throwable cause)
   {
      super(message, cause);
   }

   /**
    * Constructs checked standard Java exception with the specified cause and a detail message.
    *
    * @param cause - the cause (which is saved for later retrieval by the Throwable.getCause() method)
    */
   public CommonFunctionException(final Throwable cause)
   {
      super(cause);
   }
}
