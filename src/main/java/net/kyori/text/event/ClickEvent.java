package net.kyori.text.event;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import java.util.Locale;

import javax.annotation.concurrent.Immutable;

/**
 * A click event.
 *
 * <p>A click event processes an {@link Action} when clicked on.</p>
 */
@Immutable
public final class ClickEvent {

    /**
     * The click event action.
     */
    private final Action action;
    /**
     * The click event value.
     */
    private final String value;

    public ClickEvent(final Action action, final String value) {
        this.action = action;
        this.value = value;
    }

    /**
     * Gets the click event action.
     *
     * @return the click event action
     */
    public Action getAction() {
        return this.action;
    }

    /**
     * Gets the click event value.
     *
     * @return the click event value
     */
    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(final Object other) {
        if(this == other) return true;
        if(other == null || this.getClass() != other.getClass()) return false;
        final ClickEvent that = (ClickEvent) other;
        return this.action == that.action && Objects.equal(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.action, this.value);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("action", this.action)
            .add("value", this.value)
            .toString();
    }

    /**
     * An enumeration of click event actions.
     */
    public enum Action {
        /**
         * Opens a url when clicked.
         */
        @SerializedName("open_url")
        OPEN_URL(true),
        /**
         * Opens a file when clicked.
         *
         * <p>This action is not readable, and may only be used locally on the client.</p>
         */
        @SerializedName("open_file")
        OPEN_FILE(false),
        /**
         * Runs a command when clicked.
         */
        @SerializedName("run_command")
        RUN_COMMAND(true),
        /**
         * Suggests a command into the chat box.
         */
        @SerializedName("suggest_command")
        SUGGEST_COMMAND(true),
        /**
         * Changes the page of a book.
         */
        @SerializedName("change_page")
        CHANGE_PAGE(true);

        /**
         * If this action is readable.
         *
         * <p>When an action is not readable it will not be deserailized.</p>
         */
        private final boolean readable;

        Action(final boolean readable) {
            this.readable = readable;
        }

        /**
         * Tests if this action is readable.
         *
         * @return {@code true} if this action is readable, {@code false} if this
         *     action is not readable
         */
        public boolean isReadable() {
            return this.readable;
        }

        @Override
        public String toString() {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }
}