package org.bc.dietary;

import org.bc.sdak.ExceptionType;

public enum DietaryExceptionType implements ExceptionType{
	MethodReturnTypeError,
	ModuleInvokeError,
	MethodParameterTypeError,
	ParameterMissingError
}
