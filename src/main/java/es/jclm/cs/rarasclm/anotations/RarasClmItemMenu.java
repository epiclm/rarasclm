package es.jclm.cs.rarasclm.anotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RarasClmItemMenu {
	int orden();
	String modulo();
	String deno();
	String caption();
}
