#!/usr/bin/env bash
set -e

# Get latest tag (like "v1.4.2")
LATEST_TAG=$(git describe --tags --abbrev=0 2>/dev/null || echo "v0.0.0")

# Remove "v"
LATEST_VERSION=${LATEST_TAG#v}

IFS='.' read -r MAJOR MINOR PATCH <<< "$LATEST_VERSION"

# Get commit messages since last tag
COMMITS=$(git log $LATEST_TAG..HEAD --pretty=format:"%s")

BUMP="patch"

if echo "$COMMITS" | grep -q "feat!"; then
  BUMP="major"
elif echo "$COMMITS" | grep -q "feat:"; then
  BUMP="minor"
elif echo "$COMMITS" | grep -q "fix:"; then
  BUMP="patch"
fi

case $BUMP in
  major)
    MAJOR=$((MAJOR + 1))
    MINOR=0
    PATCH=0
    ;;
  minor)
    MINOR=$((MINOR + 1))
    PATCH=0
    ;;
  patch)
    PATCH=$((PATCH + 1))
    ;;
esac

NEW_VERSION="${MAJOR}.${MINOR}.${PATCH}"

echo "$NEW_VERSION"
