package com.agriflux.agrifluxweb.configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date> {

    @Autowired
    private MessageSource messageSource;

    public DateFormatter() {
        super();
    }

	@Override
	public String print(Date data, Locale locale) {
		final SimpleDateFormat dateFormat = createDateFormat(locale);
        return dateFormat.format(data);
	}

	@Override
	public Date parse(String data, Locale locale) throws ParseException {
		final SimpleDateFormat dateFormat = createDateFormat(locale);
        return dateFormat.parse(data);
	}
	
	private SimpleDateFormat createDateFormat(final Locale locale) {
		final String format = this.messageSource.getMessage("date.format", null, locale);
		final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(false);
		return dateFormat;
	}

}
