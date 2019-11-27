package com.example.demo;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.*;
import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * ==================================================
 *
 * @author : luowei
 * @fileName: DecimalValidator
 * @create 2019/7/12
 * @since 1.0.0
 * <description>：校验金额小数点后两位
 * ==================================================
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DecimalValidator.ValidateMoney.class})
@Documented
public @interface DecimalValidator {

	/**
	 * 返回message
	 * @return
	 */
	String message();

	/**
	 * 数字类型
	 * @return
	 */
	FieldType fieldType();

	class ValidateMoney implements ConstraintValidator<DecimalValidator, BigDecimal> {

		private int fieldType;

		@Override
		public void initialize(DecimalValidator constraintAnnotation) {
			this.fieldType = constraintAnnotation.fieldType().value();
		}

		@Override
		public boolean isValid(BigDecimal bigDecimal, ConstraintValidatorContext constraintValidatorContext) {
			String str = bigDecimal.toString();
			String pattern = "";
			switch(fieldType){
			    case 1:
					pattern = "^(([1-9]{1}\\d*)|(0{1}))(\\.\\d{1}[1-9]{1})$";
			        break;
				case 2:
					pattern = "^(([1-9]{1}\\d*)|(0{1}))(\\.\\d{0,6}[1-9]{1})$";
					break;
			    default:
			        break;
			}
			return Pattern.compile(pattern).matcher(str).matches();
		}
	}

	enum FieldType {

		/**
		 * 金额类型
		 */
		AMOUNT(1),

		/**
		 * 利率类型
		 */
		RATE(2);

		private final int value;

		FieldType(int value) {
			this.value = value;
		}

		public int value() {
			return this.value;
		}

	}

}
