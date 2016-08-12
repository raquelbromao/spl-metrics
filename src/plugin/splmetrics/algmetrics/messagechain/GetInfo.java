package plugin.splmetrics.algmetrics.messagechain;

import java.util.regex.Pattern;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

import org.eclipse.core.runtime.CoreException;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ExpressionStatement;
//import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;

/**
 * 
 * @author Romao
 * @date 29/07/16
 * @time 15:04
 * 
 *       Pega as informa��es dos m�todos de TODAS as classes do projeto
 *       principal aberto na workspace Pega o nome do m�todo e o seu tipo de
 *       retorno
 *
 */

public class GetInfo extends AbstractHandler {
	
	private static final String JDT_NATURE = "org.eclipse.jdt.core.javanature";
	// private StructuralPropertyDescriptor property;

	public static void splitMessageChain(String s) {
		// retira o ";" do final da string
		s = s.replace(";", " ");

		// Quebra a vari�vel quando acha . e armazena a sobra numa posi��o do
		// array aux
		// a().b() -> . � descartando e a() fica em aux[0] e b() em aux[1]
		String[] aux = s.split(Pattern.quote("."));

		// Pega o tamanho da string aux
		// Imprime a vari�vel aux na tela
		System.out.println("Objeto: " + aux[0]);
		for (int i = 1; i < aux.length; i++) {
			System.out.println("M�todo[" + i + "]: " + aux[i]);
		}
		
		System.out.println("________________________________________________________________________________");
	}

	public static void verificaMessageChain(String s) {
		// verifica se a express�o coletada � igual ao regex criado
		// n�o foi usado [;] no final do regex pq o compilador nem l� se n�o houver ele no final
		if (s.matches("[\\w]+([\\.]+[\\w]+[(]+[)]){2,}")) {
			System.out.println("\nMessage Chain: " + s + "\n");
			splitMessageChain(s);
		} else {
			//System.out.println("N�o � Message Chain: " + s + "\n");
		}
	}

	/*public static void testaStrings(String[] s) {
		for (int i = 0; i < s.length; i++) {
			verificaMessageChain(s[i]);
		}
	}*/

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();

		// Pega todos os projetos na workspace
		IProject[] projects = root.getProjects();

		// Faz um loop sobre todos os projetos e executa o m�todo analyseMethods
		for (IProject project : projects) {
			try {
				if (project.isNatureEnabled(JDT_NATURE)) {
					analyseMethods(project);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * private void analyseClass (IProject project) { //IProject class = root. }
	 * 
	 */

	private void analyseMethods(IProject project) throws JavaModelException {
		IPackageFragment[] packages = JavaCore.create(project).getPackageFragments();
		// parse(JavaCore.create(project));
		for (IPackageFragment mypackage : packages) {
			if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
				// System.out.println("####### INFORMA��ES DO METHOD DECLARATION
				// DO PROJETO " + mypackage.getElementName());
				// createASTmethod(mypackage);
				if (mypackage.getElementName() != null) {
					System.out.println("####### INFORMA��ES DO EXPRESSION STATEMENT DO PROJETO "
							+ mypackage.getElementName() + " ########\n");
				}
				createASTInvocation(mypackage);
			}
		}
	}

	/*
	 * private void createASTmethod(IPackageFragment mypackage) throws
	 * JavaModelException { for (ICompilationUnit unit :
	 * mypackage.getCompilationUnits()) { // now create the AST for the
	 * ICompilationUnits CompilationUnit parse = parse(unit); MethodVisitor
	 * visitor = new MethodVisitor(); parse.accept(visitor);
	 * 
	 * // Imprime na tela o nome do m�todo e o tipo de retorno for
	 * (MethodDeclaration method : visitor.getMethods()) {
	 * System.out.println("\n####### Informa��es do METHOD DECLARATION ######");
	 * //System.out.println("Class name:" +
	 * method.getParent().getClass().getName());
	 * //System.out.println("Node Type of Parent Node:" +
	 * method.getParent().getNodeType()); System.out.println("Method name: " +
	 * method.getName()); System.out.println("Return type: " +
	 * method.getReturnType2()); System.out.println("Return body: "+
	 * method.getBody()); } } }
	 */

	private void createASTInvocation(IPackageFragment mypackage) throws JavaModelException {
		for (ICompilationUnit unit : mypackage.getCompilationUnits()) {
			// now create the AST for the ICompilationUnits
			CompilationUnit parse = parse(unit);
			ExpressionInvoke visitor = new ExpressionInvoke();
			parse.accept(visitor);

			// Imprime na tela o nome do m�todo e o tipo de retorno
			for (ExpressionStatement method : visitor.getMethods()) {
				//System.out.println("\n\nNAME: " + method.getExpression());
				//System.out.println("PARENT: " + method.getParent());
				//System.out.println("ARGUMENTS: " + method.arguments());

				// Converter o method.getParent() em string e avalia se � Message Chain
				String t = null;
				t = method.getExpression().toString();

				verificaMessageChain(t);
			}
		}
	}

	/**
	 * Reads a ICompilationUnit and creates the AST DOM for manipulating the
	 * Java source file
	 *
	 * @param unit
	 * @return
	 */
	private static CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); // parse
	}
}