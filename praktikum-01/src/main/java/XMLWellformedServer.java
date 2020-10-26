import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLWellformedServer implements CommandExecutor {


	private static final String TAG_NAME_PATTERN = "<\\/?([^\\s>]*)>?";
	private static final String TAG_PATTERN = "([\\n\\t\\s])*<[^\\?][^>]*[^\\/]>|<\\/[^>]*>";

	/**
	 * Validates a specific xml file.
	 *
	 * @param command The content of the xml file.
	 * @return A string containing boolean information whether the xml file is well-formed or not.
	 * @throws Exception
	 */
	@Override
	public String execute(String command) throws Exception {
		return Boolean.toString(checkWellFormed(command));
	}

	/**
	 * Validates a specific xml file.
	 *
	 * @param content The content an xml file.
	 * @return A boolean state indicating whether the xml file is well-formed or not.
	 */
	public boolean checkWellFormed(String content) {
		Stack<String> stack = new ListStack<>();
		String tag;

		while ((tag = getNextToken(content)) != null) {
			String tagName = getTagName(tag);

			if (tagName == null) {
				return false;
			}

			if (isClosingTag(tag, tagName)) {
				String poppedTag = stack.pop();

				if (!tagName.equals(poppedTag)) {
					return false;
				}
			} else {
				stack.push(tagName);
			}
			int index = content.indexOf(tag);
			content = content.substring(index + tag.length());
		}
		return stack.isEmpty();
	}


	private String getNextToken(String content) {
		Pattern pattern = Pattern.compile(TAG_PATTERN);
		Matcher matcher = pattern.matcher(content);

		if (matcher.find()) {
			return matcher.group(0);
		}

		return null;
	}

	private String getTagName(String tag) {
		Pattern pattern = Pattern.compile(TAG_NAME_PATTERN);
		Matcher matcher = pattern.matcher(tag);

		if (matcher.find()) {
			return matcher.group(1);
		}

		return null;
	}

	private boolean isClosingTag(String tag, String tagName) {
		return (tag != null && tag.contains("</" + tagName));
	}

}

