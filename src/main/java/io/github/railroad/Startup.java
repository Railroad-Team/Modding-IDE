package io.github.railroad;

import io.github.railroad.config.Configs;
import io.github.railroad.debugger.syntax.SyntaxHandler;

public class Startup {

	public static void main(String[] args) {
//		Railroad.boot(args);
		new Configs();
		SyntaxHandler.boot(args);
	}
}
