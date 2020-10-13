package biz.idsoftware.controller;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import biz.idsoftware.exception.BookNotFoundException;
import biz.idsoftware.model.Book;
import biz.idsoftware.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping(value = "/api/book")
@Tag(name = "book", description = "the book API with documentation annotations")
public class BookController {

	@Autowired
	private BookRepository repository;

	@Operation(summary = "Get a book by its id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Found the book", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = Book.class)) }),
	  @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
	    content = @Content), 
	  @ApiResponse(responseCode = "404", description = "Book not found", 
	    content = @Content) })
	@GetMapping("/{id}")
	public Book findById(@Parameter(description = "id of book to be searched") @PathVariable long id) {
		return repository.findById(id).orElseThrow(() -> new BookNotFoundException());
	}

	@GetMapping("/")
	public Collection<Book> findBooks() {
		return repository.getBooks();
	}

	@Operation(summary = "Update a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "book updated successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))}),
            @ApiResponse(responseCode = "404", description = "No Book exists with given id", content = @Content) })
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Book updateBook(@Parameter(description = "id of book to be updated") @PathVariable("id") final String id, @RequestBody final Book book) {
		return book;
	}

	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Book patchBook(@PathVariable("id") final String id, @RequestBody final Book book) {
		return book;
	}

	@Operation(summary = "Create a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "book created", content = { @
                    Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))}),
            @ApiResponse(responseCode = "404", description = "Bad request", content = @Content) })
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Book postBook(@NotNull @Valid @RequestBody final Book book) {
		return book;
	}

	@RequestMapping(method = RequestMethod.HEAD, value = "/")
	@ResponseStatus(HttpStatus.OK)
	public Book headBook() {
		return new Book();
	}

	@Operation(summary = "Delete a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "book deleted"),
            @ApiResponse(responseCode = "404", description = "Bad request", content = @Content) })
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public long deleteBook(@Parameter(description = "id of book to be deleted") @PathVariable final long id) {
		return id;
	}
}
