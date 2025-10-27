package com.etiya.common.validators.concretes;


/*import com.etiya.common.validators.annotations.EnumValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 1. Bu sınıfın, @EnumValidator anotasyonunun MANTIĞINI içerdiğini belirtiyoruz
public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, CharSequence> {
    // 2. Geçerli enum değerlerini ("PHONE", "EMAIL" gibi) tutacağımız bir liste (Set)
    private Set<String> acceptedValues;



    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        // Anotasyonu kullanırken DTO'da belirttiğimiz enum sınıfını al
        // Örnek: @EnumValidator(enumClass = ContactMediumType.class) -> ContactMediumType.class'ı alır
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClass();

        // Enum'un içindeki tüm sabitleri ("PHONE", "EMAIL") alıp
        // yukarıda oluşturduğumuz 'acceptedValues' listesine ekle.
        acceptedValues = Stream.of(enumClass.getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        // DTO'dan gelen değer (örneğin "EMAIL" veya "WHATSAPP") bu 'value' değişkenine düşer.

        // Eğer değer boş (null) ise, biz karışmıyoruz.
        // Bu durumu @NotBlank veya @NotNull anotasyonu zaten kontrol etmeli.
        if (value == null) {
            return true;
        }

        // Gelen değer ("WHATSAPP"), bizim başta oluşturduğumuz geçerli değerler listesinde ("PHONE", "EMAIL") var mı?
        // Varsa -> true (validasyon başarılı)
        // Yoksa -> false (validasyon başarısız)
        return acceptedValues.contains(value.toString());
    }

}*/