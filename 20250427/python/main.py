import cv2
from PIL import Image


RTSP_URL = "rtsp://localhost:8554/mystream?rtsp_transport=tcp"


def main():
    print(f"🚀 Connecting to {RTSP_URL}")

    cap = cv2.VideoCapture(RTSP_URL, cv2.CAP_FFMPEG)

    cap.set(cv2.CAP_PROP_OPEN_TIMEOUT_MSEC, 5000)
    cap.set(cv2.CAP_PROP_READ_TIMEOUT_MSEC, 5000)

    if not cap.isOpened():
        print("❌ Failed to open RTSP stream")
        return

    _, frame = cap.read()

    img = Image.fromarray(frame, "RGB")
    img.save("data/image.png")

    cap.release()

    print("✅ Image saved as data/image.png")


main()
