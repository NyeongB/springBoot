package hello.itemservice.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import static org.assertj.core.api.Assertions.*;

import java.util.Locale;

@SpringBootTest
public class MessageSourceTest {

	@Autowired
	MessageSource ms;

	@Test
	void helloMessage() {
		String result = ms.getMessage("hello", null, null);
		assertThat(result).isEqualTo("�ȳ�");
	}

	@Test
	void notFoundMessageCode() {
		assertThatThrownBy(() -> ms.getMessage("no_code", null, null)).isInstanceOf(NoSuchMessageException.class);
	}

	@Test
	void notFoundMessageCodeDefaultMessage() {
		String result = ms.getMessage("no_code", null, "�⺻ �޽���", null);
		assertThat(result).isEqualTo("�⺻ �޽���");
	}

	@Test
	void argumentMessage() {
		String result = ms.getMessage("hello.name", new Object[] { "Spring" }, null);
		assertThat(result).isEqualTo("�ȳ� Spring");
	}

	@Test
	void defaultLang() {
		assertThat(ms.getMessage("hello", null, null)).isEqualTo("�ȳ�");
		assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("�ȳ�");
	}

	@Test
	void enLang() {
		assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
	}
}