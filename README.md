# Notes

## Improvements
- Double buffering was implemented to avoid flickering (especially prevalent on Windows machines).
- Colors now match requirements.
- Frame rate is now independent of fall speed.

## Deviations
- The falling speed requires a little more adjusting for better player experience. By level 3, the fall speed is probably a little too fast.
- Cursor detection on the active shape does not utilize the Point-in-polygon test. This is because of how the active shape perceives itself relative to the canvas. It was easier to calculate the unit square position of the cursor than to conduct the Point-in-polygon test which requires re-derivation of the device coordinates to achieve the polygon vertices. Had I known this was to be a requirement later on, I would've architected my data models to account for this operation.

## Known bugs
- You are able to rotate out of the play area
- Adjusting the canvas width / height can cause problems during gameplay

# Installation

For a developer installation, you can do either:

Recommended:

- `./scripts/<OS>/install.sh`
- `./scripts/<OS>/run.sh`

Or:

- `cd src && javac Main.java`
- `java Main`
