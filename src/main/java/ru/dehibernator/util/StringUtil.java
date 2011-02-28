package ru.dehibernator.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class StringUtil {
	public static String ucFirst(String s) {
		if (s == null)
			return null;
		else if (s.isEmpty())
			return "";
		else
			return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	public static String lcFirst(String s) {
		if (s == null)
			return null;
		else if (s.isEmpty())
			return "";
		else
			return s.substring(0, 1).toLowerCase() + s.substring(1);
	}

	public static String escapeQuotes(String s) {
		return s.replace("\"", "\\\"");
	}

	public static String implode(String glue, String... parts) {
		return implode(glue, Arrays.asList(parts));
	}

	public static String implode(String glue, Collection<String> parts) {
		if (parts == null)
			return "";

		if (parts.isEmpty())
			return "";

		if (glue == null)
			glue = "";

		Collection<String> newParts = new ArrayList<String>();
		for (String part : parts) {
			if (part == null)
				continue;

			part = part.trim();
			if (part.isEmpty())
				continue;

			newParts.add(part);
		}

		StringBuffer sb = new StringBuffer();
		int i = 1;
		int size = newParts.size();
		for (String part : newParts) {
			sb.append(part);
			if (i < size)
				sb.append(glue);
			i++;
		}

		return sb.toString();
	}

	public static String implodeByComma(Collection<String> parts) {
		return implode(", ", parts);
	}

	public static String implode(String glue, int n, String part) {
		StringBuffer sb = new StringBuffer();

		boolean isFirst = true;

		for (int i = 0; i < n; i++) {
			if (isFirst)
				isFirst = false;
			else
				sb.append(glue);

			sb.append(part);
		}

		return sb.toString();
	}

	public static String implodeAndWrap(String glue, String leftWrapper, String rightWrapper,
			Collection<String> parts) {
		StringBuffer sb = new StringBuffer();
		boolean isFirst = true;

		for (String part : parts) {
			if (isFirst)
				isFirst = false;
			else
				sb.append(glue);

			sb.append(leftWrapper + part + rightWrapper);
		}

		return sb.toString();
	}

	public static String htmlEncode(String value) {
		return value == null ? value : value.replace("&", "&amp;").replace(">", "&gt;")
				.replace("<", "&lt;").replace("\"", "&quot;").replace("'", "&apos;");
	}

	public static String stripTags(String html) {
		return html != null ? html.replaceAll("\\<.*?\\>", "") : null;
	}

	public static String htmlToText(String html) {
		if (html == null)
			return null;

		html = html.replace("\r\n", "");
		html = html.replace("\n", "");
		html = html.replaceAll("(?i)<\\s*br\\s*/?>", "\n");

		return stripTags(html);
	}

	public static String nlToBr(String s) {
		return s != null ? s.replaceAll("\r\n", "<br>").replaceAll("\n", "<br>") : null;
	}

	public static String stripNbsp(String s) {
		return s != null ? s.replaceAll("&nbsp;", " ") : null;
	}

	public static String prefix(String prefix, String s) {
		if (s == null || s.isEmpty())
			return "";
		else
			return prefix + s;
	}

	public static String trim(String s) {
		if (s == null)
			return null;

		s = s.trim();
		if (s.isEmpty())
			return null;
		else
			return s;
	}

	public static boolean isEmpty(String s) {
		return s == null || s.trim().isEmpty();
	}

	public static String cut(String s, int max) {
		if (s == null)
			return null;
		if (s.length() < max)
			return s;
		return s.substring(0, max) + "...";
	}

	public static Collection<String> splitWords(String s) {
		String[] partsArray = s.trim().split("\\s+");
		Collection<String> parts = new ArrayList<String>();
		for (String part : partsArray) {
			parts.add(part.trim());
		}
		return parts;
	}

	public static String nullIfEmpty(String s) {
		if (s == null)
			return null;
		if (s.isEmpty())
			return null;
		return s;
	}
}
