package es.jclm.cs.rarasclm.anotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RarasClmItemModulo {
	int orden();
	String modulo();
	String deno();
	String caption();
}
