# AGENTS.md

## User Authority Protocol (ABSOLUTE PRIORITY)

### When User Says You Are Wrong

1. Acknowledge immediately.
2. STOP - say nothing more.
3. WAIT for instructions.

### When Uncertain

1. STOP immediately.
2. Do not guess, assume, or explain.
3. WAIT for user clarification.

## Git Commit Message Protocol (MANDATORY)

### Before Writing Any Commit Message

**REQUIRED STEPS:**

1. Call `get_changed_files` with `sourceControlState: ["staged"]`.
2. Verify the response contains staged files with diffs.
3. If the list is empty, STOP and inform the user.
4. Only then write a commit message based on verified content.

**PROHIBITED:**

- Writing commit messages without verification.
- Assuming or guessing what is staged.
- Proceeding when verification returns an empty result.

## Terminal Policy

**NEVER** use the `run_in_terminal` tool or suggest terminal commands.

## Korean Communication

Respond in Korean unless instructed otherwise. Technical terms may pair the
English word with its Korean transliteration when helpful.

## Git Commit Message Format

Based on:

- <https://github.com/agis/git-style-guide>
- <https://cbea.ms/git-commit/>

### Rules

1. Use imperative mood (e.g., "Add feature", "Fix bug").
2. Communicate intent and purpose, not every detail.
3. Summary: maximum 50 characters.
4. Body: wrap at 72 characters, one sentence per line.
5. No bullet points in the body; write full sentences.
6. Write in English first, then add Korean translation below.
7. Korean translation will be removed before committing.

### Character Count Reference

```txt
|----+----1----+----2----+----3----+----4----+----5|
Summary must not exceed this line (50 chars)

|----+----1----+----2----+----3----+----4----+----5----+----6----+----7|
Body text must not extend beyond this point (72 chars)
```

### Message Structure

```txt
[Summary] - Max 50 chars, imperative mood

[First paragraph] - Line breaks at the end of each sentence. Each complete
thought should live on its own line. This keeps the log easy to scan by
highlighting the "why" behind the change, not just the "what".

[Additional paragraphs] - Separate topics with blank lines. Each paragraph
covers one aspect of the change.

---

Translate the English summary and body into Korean for reference before
committing.
```

### Example

```txt
Add health check API and update documentation

Implement a new API endpoint for health checks to monitor service status.
This allows external systems to verify our service is operational. The
endpoint returns HTTP 200 when healthy and 503 when unhealthy.

Update the documentation to describe the new endpoint and its usage. Include
examples for common monitoring scenarios and integration patterns. Add a
troubleshooting section for potential configuration issues.
```

## Feature-Sliced Design Guidelines

1. Keep only FSD layer directories (for example `widgets`, `features`,
   `shared`) at the `src` root, listed in their hierarchy order.
2. Place page-level UI fragments in `src/widgets/<widget>/ui/*` and import
   the component file directly when needed.
3. When another layer consumes a widget, import the actual implementation
   path (for example `../widgets/widget/ui/Widget`) to keep boundaries clear.
4. Add subdirectories such as `model`, `lib`, or `api` only when there is
   real logic to store, and avoid empty folders.
5. Before creating a new component, define its layer and responsibility, and
   then create the directory structure that fits that scope.
