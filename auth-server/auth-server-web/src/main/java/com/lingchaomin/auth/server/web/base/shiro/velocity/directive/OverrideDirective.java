package com.lingchaomin.auth.server.web.base.shiro.velocity.directive;

import com.lingchaomin.auth.server.web.base.shiro.velocity.utils.VelocityUtils;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;

import java.io.IOException;
import java.io.Writer;

/**
 * @author: mlc
 * @date: 2016年7月6日
 * @Description: override指令
 */

public class OverrideDirective extends Directive {

	@Override
	public String getName() {
		return "override";
	}

	@Override
	public int getType() {
		return BLOCK;
	}

	/**
	 * 定义override指令
	 * @param context
	 * @param writer
	 * @param node
	 * @return
	 * @throws IOException
	 * @throws ResourceNotFoundException
	 * @throws ParseErrorException
	 * @throws MethodInvocationException
	 */
	@Override
	public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
		String name = VelocityUtils.getRequiredArgument(context, node, 0, getName());
		OverrideNodeWrapper override = (OverrideNodeWrapper) context.get(VelocityUtils.getOverrideVariableName(name));
		if (override == null) {
			Node body = node.jjtGetChild(1);
			context.put(VelocityUtils.getOverrideVariableName(name), new OverrideNodeWrapper(body));
		} else {
			OverrideNodeWrapper current = new OverrideNodeWrapper(node.jjtGetChild(1));
			VelocityUtils.setParentForTop(current, override);
		}
		return true;
	}

	public static class OverrideNodeWrapper extends SimpleNode {
		public Node current;
		public OverrideNodeWrapper parentNode;

		public OverrideNodeWrapper(Node node) {
			super(1);
			this.current = node;
		}

		public boolean render(InternalContextAdapter context, Writer writer)
				throws IOException, MethodInvocationException, ParseErrorException, ResourceNotFoundException {
			OverrideNodeWrapper preNode = (OverrideNodeWrapper) context.get(VelocityUtils.OVERRIDE_CURRENT_NODE);
			try {
				context.put(VelocityUtils.OVERRIDE_CURRENT_NODE, this);
				return current.render(context, writer);
			} finally {
				context.put(VelocityUtils.OVERRIDE_CURRENT_NODE, preNode);
			}
		}

	}
}
