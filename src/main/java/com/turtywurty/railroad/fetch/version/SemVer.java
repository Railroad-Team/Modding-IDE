package com.turtywurty.railroad.fetch.version;

public class SemVer implements Comparable<SemVer> {

	private final Integer major;
	private final Integer minor;
	private Integer patch;

	public SemVer(String versionString) {
		String[] vs = versionString.split("\\.");
		this.major = Integer.parseInt(vs[0]);
		this.minor = Integer.parseInt(vs[1]);

		if (vs.length == 3)
			this.patch = Integer.parseInt(vs[2]);
	}

	public int getMajor() {
		return major;
	}

	public int getMinor() {
		return minor;
	}

	public int getPatch() {
		return patch;
	}

	@Override
	public int compareTo(SemVer other) {
		if (Integer.compare(this.major, other.major) != 0)
			return Integer.compare(this.major, other.major);

		if (Integer.compare(this.minor, other.minor) != 0)
			return Integer.compare(this.minor, other.minor);

		if (this.patch == null ^ other.patch == null)
			return this.patch == null ? -1 : 1;

		return Integer.compare(this.patch, other.patch);
	}

	@Override
	public String toString() {
		if (this.patch == null) {
			return String.format("%d.%d", major, minor);
		} else {
			return String.format("%d.%d.%d", major, minor, patch);
		}
	}
}