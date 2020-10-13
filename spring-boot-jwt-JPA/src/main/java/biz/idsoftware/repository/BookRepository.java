package biz.idsoftware.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import biz.idsoftware.model.Book;

@Repository
public class BookRepository {
	private Map<Long, Book> books = new HashMap<>();

	public Optional<Book> findById(long id) {
		return Optional.ofNullable(books.get(id));
	}

	public void add(Book book) {
		books.put(book.getId(), book);
	}

	public Collection<Book> getBooks() {
		return books.values();
	}
}
